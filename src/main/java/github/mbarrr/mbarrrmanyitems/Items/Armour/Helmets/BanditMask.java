package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;

import java.util.ArrayList;
import java.util.List;


/**
 * Gives wearer speed
 */

/**
 * Credits
 * FIND CREDITS
 */

public class BanditMask extends Helmet {

    public BanditMask(MbarrrManyItems instance) {
        super(9, instance, "Bandit Mask");

        String title = "Bandit Mask";
        List<String> lore = new ArrayList<>();

        setDisplayAttributes(title, lore);
    }
}
