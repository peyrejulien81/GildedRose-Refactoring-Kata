package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            /*Swith (items.name) {
                case "Aged Brie" :
                    break;
                case "Backstage passes to a TAFKAL80ETC concert" :
                    break;
                case "Sulfuras, Hand of Ragnaros" : // aucun traitement, on passe au suivant
                    break;
                default:
            }*/

            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        decreaseQuality(items[i]);
                    }
            } else {
                increaseQuality(items[i]);

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            increaseQuality(items[i]);
                        }

                        if (items[i].sellIn < 6) {
                            increaseQuality(items[i]);
                        }
                    }
            }

            decreaseSellIn(items[i]);

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                decreaseQuality(items[i]);
                            }
                    } else {
                        totalLossOfQuality(items[i]);
                    }
                } else {
                    increaseQuality(items[i]);
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
