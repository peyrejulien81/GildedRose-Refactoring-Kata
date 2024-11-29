package com.gildedrose;

class GildedRose {
    Item[] items;

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            switch (items[i].name) {
                case AGED_BRIE :
                    increaseQuality(items[i]);
                    break;
                case BACKSTAGE_PASSES :
                    increaseQuality(items[i]);
                    if (items[i].sellIn < 11) {
                        increaseQuality(items[i]);
                    }
                    if (items[i].sellIn < 6) {
                        increaseQuality(items[i]);
                    }
                    break;
                case SULFURAS :
                    // aucun traitement, on passe au suivant
                    break;
                case CONJURED:
                    decreaseQuality(items[i]);
                    decreaseQuality(items[i]);
                    break;
                default:
                    decreaseQuality(items[i]);
            }

            decreaseSellIn(items[i]);

            // le produit devient périmé
            if (items[i].sellIn < 0) {
                switch (items[i].name) {
                    case AGED_BRIE:
                        increaseQuality(items[i]);
                        break;
                    case BACKSTAGE_PASSES:
                        totalLossOfQuality(items[i]);
                        break;
                    default:
                }
            }
        }
    }

    public static void totalLossOfQuality(Item item) {
        item.quality = 0;
    }

    public static void decreaseSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
        }
    }

    public static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    public static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}
