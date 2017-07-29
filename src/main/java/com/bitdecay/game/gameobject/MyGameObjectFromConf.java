package com.bitdecay.game.gameobject;

import com.bitdecay.game.Launcher;
import com.bitdecay.game.component.AbstractComponent;
import com.bitdecay.game.component.NameComponent;
import com.bitdecay.game.component.PositionComponent;
import com.typesafe.config.Config;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Parse the config files for game objects and initialize them here
 */
public final class MyGameObjectFromConf {

    private static Logger log = Logger.getLogger(MyGameObjectFactory.class);
    private static Config gobsConf = Launcher.conf.getConfig("gobs");
    private static List<Config> defaultConf = gobsConf.getConfigList("default").stream().map(Config.class::cast).collect(Collectors.toList());
    private static List<Config> listConf = gobsConf.getConfigList("list").stream().map(Config.class::cast).collect(Collectors.toList());
    static {
        List<Config> extendedConfs = gobsConf.getConfigList("external").stream().map(Config.class::cast).collect(Collectors.toList());
        List<Config> extendedLists = new ArrayList<>();
        extendedConfs.forEach(conf -> extendedLists.addAll(conf.getConfigList("list")));
        listConf.addAll(extendedLists);
    }

    private MyGameObjectFromConf(){}

    public static List<Config> objectConfs(){
        List<Config> list = new ArrayList<>();
        list.addAll(listConf);
        return list;
    }

    public static MyGameObject objectFromConf(String name, float x, float y){
        Optional<Config> conf = configForObjectName(name);
        MyGameObject obj = new MyGameObject();
        obj.addComponent(new NameComponent(name))
                .addComponent(new PositionComponent(x, y));
        List<Config> componentsList = componentConfigListForConfig(conf);
        componentsList.forEach(componentConf -> {
            String className = "com.bitdecay.game.component." + componentConf.getString("name") + "Component";
            try {
                Class componentClass = Class.forName(className);
                try {
                    Constructor<? extends AbstractComponent> componentConstructorWithConf = componentClass.getConstructor(Config.class);
                    obj.addComponent(componentConstructorWithConf.newInstance(componentConf));
                }  catch (NoSuchMethodException a) {
                    try {
                        Constructor<? extends AbstractComponent> componentConstructor = componentClass.getConstructor();
                        obj.addComponent(componentConstructor.newInstance());
                    } catch (NoSuchMethodException b) {
                        err("Could not construct component with name: " + className + " for object " + name + " (Tip: look in the component class, there must be a constructor that takes only a Config or an empty constructor)", b);
                    }
                }
            } catch (ClassNotFoundException e) {
                err("Could not find class with name: " + className + " for object " + name);
            } catch (InvocationTargetException e){
                err("There was a problem creating " + className + " for object " + name + " (Tip: your conf file is probably missing a key:value or the key is misspelled)", e.getCause());
            } catch (Exception e){
                err("General exception creating " + className + " for object " + name, e);
            }
        });
        obj.cleanup();
        //log.info("Create object from conf: " + obj);
        return obj;
    }

    private static Optional<Config> configForObjectName(String name){
        return listConf.stream().filter(c -> c.getString("name").equalsIgnoreCase(name)).findFirst();
    }

    private static List<Config> componentConfigListForConfig(Optional<Config> conf){
        List<Config> components = componentConfigListForConfigRecursive(conf);
        doExtend(components, defaultConf);
        return components;
    }

    private static List<Config> componentConfigListForConfigRecursive(Optional<Config> confOpt){
        return confOpt.map(conf -> {
            List<Config> components = conf.getConfigList("components").stream().map(Config.class::cast).collect(Collectors.toList());
            if (conf.hasPath("extends")) {
                List<String> extNames = conf.getStringList("extends");
                extNames.forEach(extName -> doExtend(components, componentConfigListForConfigRecursive(configForObjectName(extName))));
            }
            return components;
        }).orElse(Collections.emptyList());
    }

    private static void doExtend(List<Config> original, List<Config> extended){
        List<String> componentNames = original.stream().map(config -> config.getString("name")).collect(Collectors.toList());
        extended.forEach(extendedConf -> {
            String extendedName = extendedConf.getString("name");
            boolean allowMultiple = false;
            if (extendedConf.hasPath("allowMultiple")) allowMultiple = extendedConf.getBoolean("allowMultiple");
            // do not overwrite with extended values
            if (!componentNames.contains(extendedName) || allowMultiple) original.add(extendedConf);
        });
    }

    private static void err(String msg, Throwable cause){
        throw new RuntimeException("ConfToGameObj: " + msg, cause);
    }

    private static void err(String msg){
        throw new RuntimeException("ConfToGameObj: " + msg);
    }
}
