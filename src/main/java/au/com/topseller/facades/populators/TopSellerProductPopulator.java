package au.com.topseller.facades.populators;

public class TopSellerProductPopulator extends ProductPopulator {

    @Override
    public void populate(final ProductModel source, final ProductData target) {
        super.populate(source, target);
        target.setTopSeller(source.getTopSeller());
    }
}
