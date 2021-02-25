package com.occydaboss.skyblock.recipes;

import com.occydaboss.skyblock.SkyBlock;
import com.occydaboss.skyblock.util.AddPrefix;
import com.occydaboss.skyblock.util.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class Recipes implements Listener
{
    public static ShapedRecipe enchDiamondRecipe ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "ench_diamond_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.enchDiamond);
        recipe.shape(" d ", "ddd", " d ");
        recipe.setIngredient('d', Material.DIAMOND);
        return recipe;
    }


    public static ShapedRecipe enchFleshRecipe ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "ench_flesh_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobSummon());
        recipe.shape(" d ", "dfd", " d ");
        recipe.setIngredient('d', new RecipeChoice.ExactChoice(CustomItems.enchDiamond));
        recipe.setIngredient('f', Material.ROTTEN_FLESH);
        return recipe;
    }

    public static ShapedRecipe bobSwordRecipe ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_sword_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobSword());
        recipe.shape(" f ", " f ", " s ");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        recipe.setIngredient('s', Material.STICK);
        return recipe;
    }

    public static ShapedRecipe bobAxeRecipe1 ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_axe_recipe1");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobAxe());
        recipe.shape(" ff", " sf", " s ");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        recipe.setIngredient('s', Material.STICK);
        return recipe;
    }

    public static ShapedRecipe bobAxeRecipe2 ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_axe_recipe2");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobAxe());
        recipe.shape("ff ", "fs ", " s ");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        recipe.setIngredient('s', Material.STICK);
        return recipe;
    }

    public static ShapedRecipe bobHelmetRecipe_1 ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_helmet_recipe1");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobHelmet());
        recipe.shape("fff", "f f", "   ");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        return recipe;
    }

    public static ShapedRecipe bobHelmetRecipe_2 ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_helmet_recipe2");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobHelmet());
        recipe.shape("   ", "fff", "f f");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        return recipe;
    }

    public static ShapedRecipe bobBootsRecipe_1 ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_boots_recipe1");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobBoots());
        recipe.shape("f f", "f f", "   ");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        return recipe;
    }

    public static ShapedRecipe bobBootsRecipe_2 ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_boots_recipe2");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobBoots());
        recipe.shape("   ", "f f", "f f");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        return recipe;
    }

    public static ShapedRecipe bobChestplateRecipe ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_chestplate_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobChestplate());
        recipe.shape("f f", "fff", "fff");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        return recipe;
    }

    public static ShapedRecipe bobLeggingsRecipe ()
    {
        NamespacedKey key = new NamespacedKey(SkyBlock.mainInstance, "bob_leggings_recipe");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.bobLeggings());
        recipe.shape("fff", "f f", "f f");
        recipe.setIngredient('f', new RecipeChoice.ExactChoice(CustomItems.meatOfTheUndead));
        return recipe;
    }

}

