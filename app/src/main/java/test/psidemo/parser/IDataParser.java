package test.psidemo.parser;

import test.psidemo.model.PsiData;

/**
 * Parses data.
 */

public interface IDataParser {
    /*
     * Parses the given JSON string data into a PsiData.
     *
     * @param data The JSON string.
     * @return The parsed input as a PsiData.
     */
    PsiData parse(String data);
}
