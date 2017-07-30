package com.bitdecay.game.gameobject;

import com.bitdecay.game.component.AbstractComponent;
import com.bitdecay.game.trait.ICleanup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * This is the main game object class (called MyGameObject because of a name collision with Jump).  It contains a list of components and THAT'S IT!  It should NOT contain any real game logic.  The only extra login in this class has to do with working with the components of the class.  When you add or remove a component to a game object, it won't be added directly to the list of components.  It goes through an intermediary step before being added so that you avoid any ConcurrentModification exceptions.  The game object is dirty when you add or remove one of the components.
 */
public class MyGameObject implements ICleanup {

    protected boolean dirty = false;

    private List<AbstractComponent> components = new ArrayList<>();
    private List<AbstractComponent> componentsToAdd = new ArrayList<>();
    private List<AbstractComponent> componentsToRemove = new ArrayList<>();

    public List<AbstractComponent> getAllComponents() {
        ArrayList<AbstractComponent> all = new ArrayList<>();
        all.addAll(components);
        all.addAll(componentsToAdd);
        return all;
    }

    public boolean hasComponents(Class<?>... componentClasses){
        return ! Arrays.stream(componentClasses).filter(componentClass -> ! components.stream().filter(componentClass::isInstance).findFirst().isPresent()).findFirst().isPresent();
    }

    public boolean hasComponent(Class<?> componentClass){
        return components.stream().filter(componentClass::isInstance).findFirst().isPresent();
    }

    public <T extends AbstractComponent> Optional<T> getComponent(Class<T> componentClass){
        return components.stream().filter(componentClass::isInstance).findFirst().map(componentClass::cast);
    }

    public Optional<AbstractComponent> getComponent(AbstractComponent component){
        return components.stream().filter(component::equals).findFirst();
    }

    public <T> Optional<AbstractComponent> getComponentByIndex(Class<T> componentClass, int index){


        List<AbstractComponent> list = components.stream().filter(component -> component.getClass() == componentClass).collect(Collectors.toList());

        if (list.size() > index)
            return Optional.of(list.get(index));
        else
            return Optional.empty();
    }


    public <T> int getComponentCount(Class<T> componentClass) {
        return (int) components.stream().filter(component -> component.getClass() == componentClass).count();
    }

    public <T> void forEachComponentDo(Class<T> componentClass, Consumer<T> doFunc){
        components.stream().filter(componentClass::isInstance).map(componentClass::cast).forEach(doFunc);
    }

    public <T> void forEach(Class<T> componentClass, Consumer<T> doFunc){
        forEachComponentDo(componentClass, doFunc);
    }

    /**
     * This method does not actually add the component.  It just marks the component as one to be added before the next cycle.
     */
    public MyGameObject addComponent(AbstractComponent component){
        dirty = true;
        componentsToAdd.add(component);
        return this;
    }

    /**
     * This is a way to add a component but it assumes that the component has a constructor with just a MyGameObject
     */
    public <T extends AbstractComponent> MyGameObject addComponent(Class<T> clazz){
        try {
            Constructor<T> constructor = clazz.getConstructor();
            return addComponent(constructor.newInstance());
        } catch (Exception e){
            throw new RuntimeException("Expected there to be an empty constructor.", e);
        }
    }

    public void addComponents(List<AbstractComponent> components) {
        dirty = true;
        componentsToAdd.addAll(components);
    }

    /**
     * This method does not actually remove the component.  It just marks the component as one to be removed before the next cycle.
     */
    public <T extends AbstractComponent> Optional<T> removeComponent(Class<T> componentClass){
        Optional<T> comp = getComponent(componentClass);
        if (comp.isPresent()) {
            componentsToRemove.add(comp.get());
            dirty = true;
        }
        return comp;
    }

    /**
     * This method does not actually remove the component.  It just marks the component as one to be removed before the next cycle.
     */
    public boolean removeComponent(AbstractComponent component){
        if (components.contains(component)) {
            componentsToRemove.add(component);
            dirty = true;
            return true;
        } else return false;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void cleanup() {
        forEach(ICleanup.class, ICleanup::cleanup);
        componentsToRemove.forEach(components::remove);
        componentsToRemove.clear();
        componentsToAdd.forEach(components::add);
        componentsToAdd.clear();
        dirty = false;
    }

    @Override
    public String toString(){
        return components.toString();
    }
}
