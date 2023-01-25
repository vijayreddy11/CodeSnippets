public class Cure {
    public static void main(String args[])
    {
        String entity="ENTITY1_PROP";
        String str[]={"id", "preferred_name", "primarysource", "primaryidentifier", "phewas_code", "ukbb_code", "icd10_code", "icd9_code", "mim_number", "synonyms", "efo_termite_mappings", "efocode"};
        for(String s: str)
            System.out.print("col(\"metadata."+entity+"."+s+"\").alias(\"metadata_"+entity+"_"+s+"\"), ");



        //col("metadata.ENTITY1_PROP.ABBVIE_PHENOTYPE_ID").alias("metadata_ENTITY1_PROP_ABBVIE_PHENOTYPE_ID")
    }
}
