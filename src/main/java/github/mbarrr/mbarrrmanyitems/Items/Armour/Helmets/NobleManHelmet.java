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

public class NobleManHelmet extends Helmet {
    public NobleManHelmet(MbarrrManyItems instance) {
        super(6, instance, "Nobleman's Helmet");

        String title = "Nobleman Helmet";
        List<String> lore = new ArrayList<>();

        setDisplayAttributes(title, lore);
    }
}
