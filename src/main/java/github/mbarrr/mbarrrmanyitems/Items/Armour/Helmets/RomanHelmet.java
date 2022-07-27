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

public class RomanHelmet extends Helmet {
    public RomanHelmet(MbarrrManyItems instance) {
        super(3, instance, "Roman Helmet");

        String title = "Roman Helmet";
        List<String> lore = new ArrayList<>();

        setDisplayAttributes(title, lore);
    }
}
