package au.com.topseller.solrfacetsearch.search.providers;

import static java.util.Objects.nonNull;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;
import de.hybris.platform.solrfacetsearch.provider.impl.ValueProviderParameterUtils;

public class ProductTopSellerValueResolver extends AbstractValueResolver<ProductModel, Object, Object> {
    public static final String OPTIONAL_PARAM = "optional";
    public static final boolean OPTIONAL_PARAM_DEFAULT_VALUE = true;

    @Override
    protected void addFieldValues(InputDocument inputDocument, IndexerBatchContext indexerBatchContext,
                                  IndexedProperty indexedProperty, ProductModel productModel,
                                  ValueResolverContext<Object, Object> valueResolverContext)
            throws FieldValueProviderException {
        final Long topSellerRank = productModel.getTopSeller();
        if (nonNull(topSellerRank)) {
            inputDocument.addField(indexedProperty, topSellerRank, valueResolverContext.getFieldQualifier());
        } else {
            final boolean isOptional = ValueProviderParameterUtils.getBoolean(indexedProperty, OPTIONAL_PARAM,
                    OPTIONAL_PARAM_DEFAULT_VALUE);
            if (!isOptional) {
                throw new FieldValueProviderException(
                        "No value resolved for indexed property " + indexedProperty.getName());
            }
        }
    }
}
