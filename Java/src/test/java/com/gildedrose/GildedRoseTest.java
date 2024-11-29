package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    @DisplayName("La qualité d'un produit jamais négative")
    void qualityNeverNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @DisplayName("Aged Brie augmente sa qualité plus le temps passe")
    void agedBrieQualityIncrease() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 11, 4), // expected quality +1
            new Item("Aged Brie", 4, 20), // expected quality +1
            new Item("Aged Brie", 0, 0) // expected quality +2
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(10, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);
        assertEquals(3, app.items[1].sellIn);
        assertEquals(21, app.items[1].quality);
        assertEquals(-1, app.items[2].sellIn);
        assertEquals(1, app.items[2].quality);

    }

    @Test
    @DisplayName("la qualité d'un produit n'est jamais de plus de 50")
    void productQualityNotExceed50() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 0, 49),
            new Item("Aged Brie", -3, 49),
            new Item("foo", 4, 49)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
        assertEquals(-4, app.items[1].sellIn);
        assertEquals(50, app.items[1].quality);
        assertEquals(3, app.items[2].sellIn);
        assertEquals(48, app.items[2].quality);
    }

    @Test
    @DisplayName("Sufluras augmente sa qualité plus le temps passe")
    void sufluras() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Sulfuras, Hand of Ragnaros", 16, 80),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
        assertEquals(-1, app.items[1].sellIn);
        assertEquals(80, app.items[1].quality);
        assertEquals(16, app.items[2].sellIn);
        assertEquals(80, app.items[2].quality);
    }

    @Test
    @DisplayName("BackStage passes augment sa qualité plus le temps passe : +2 si <10jours; +3 si <5jours")
    void backStagePasses() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 3), //
            new Item("Backstage passes to a TAFKAL80ETC concert", 9, 14), // +2 si <10jours;
            new Item("Backstage passes to a TAFKAL80ETC concert", 4, 21), // +3 si <5jours
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 36), // 0
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
        assertEquals(8, app.items[1].sellIn);
        assertEquals(16, app.items[1].quality);
        assertEquals(3, app.items[2].sellIn);
        assertEquals(24, app.items[2].quality);
        assertEquals(-1, app.items[3].sellIn);
        assertEquals(0, app.items[3].quality);
    }

    @Test
    @DisplayName("Conjured voient leur qualité se dégrder 2 fois plus ite que les objets normaux")
    void conjured() {
        Item[] items = new Item[] {
            new Item("Conjured", 15, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }




}
