package ma.uiass.eia.pds.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {


        DmController ddm=new DmController();

        // System.out.println(ddm.deleteDM("Scanner000"));




        RevisionController rev=new RevisionController();

        rev.update_etat_ambu(1,"F");

        System.out.println(rev.getPrediction("ALIF1291").getEntity().toString());

    }



}
