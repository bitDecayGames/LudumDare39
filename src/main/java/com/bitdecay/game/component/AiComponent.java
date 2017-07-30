package com.bitdecay.game.component;

import com.bitdecay.game.ai.AbstractAiBehavior;
import com.bitdecay.game.ai.AiBehaviorFactory;
import com.typesafe.config.Config;

public class AiComponent extends AbstractComponent {
    public AbstractAiBehavior behavior;

    public AiComponent(Config conf) {
        this(AiBehaviorFactory.fromName(conf.getString("behavior")));
    }

    public AiComponent(AbstractAiBehavior behavior){
        this.behavior = behavior;
    }
}
