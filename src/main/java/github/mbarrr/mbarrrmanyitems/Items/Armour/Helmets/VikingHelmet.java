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

public class VikingHelmet extends Helmet {
    public VikingHelmet(MbarrrManyItems instance) {
        super(5, instance, "Viking Helmet");

        String title = "Viking Helmet";
        List<String> lore = new ArrayList<>();

        setDisplayAttributes(title, lore);
    }
}
