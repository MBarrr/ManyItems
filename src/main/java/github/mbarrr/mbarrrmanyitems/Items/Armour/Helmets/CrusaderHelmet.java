package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;

import java.util.ArrayList;
import java.util.List;

/**
 * TEXTURE CREDITS
 * Zoom31
 * https://www.planetminecraft.com/member/zoom31/
 *
 */

public class CrusaderHelmet extends Helmet {
    public CrusaderHelmet(MbarrrManyItems instance) {
        super(1, instance, "Crusader Helmet");

        String title = "Crusader Helmet";
        List<String> lore = new ArrayList<>();

        setDisplayAttributes(title, lore);
    }
}
