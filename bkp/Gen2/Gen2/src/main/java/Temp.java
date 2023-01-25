public class Temp {
    public static void main(String args[])
    {

        String str[]={"chembl_id","drug_name","inchi_key","anumber","molecule_type","primaryidentifier"};
        //col("METADATA.REL_PROP.MEASURED_AS_TOTAL_MAB").alias("metadata_rel_prop_measured_as_total_mab")
        for(String s: str)
            System.out.println("col(\"METADATA.ENTITY2_PROP."+s.toUpperCase()+"\").alias(\"metadata_entity2_prop_"+s+"\"),");
    }
}
