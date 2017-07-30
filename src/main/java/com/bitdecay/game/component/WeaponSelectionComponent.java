package com.bitdecay.game.component;

public class WeaponSelectionComponent extends AbstractComponent {
   public int selectedIndex = 0;

    public WeaponSelectionComponent() {

    }

    public WeaponSelectionComponent(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
}
