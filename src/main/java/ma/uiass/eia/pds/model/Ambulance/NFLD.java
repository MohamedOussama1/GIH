package ma.uiass.eia.pds.model.Ambulance;

import ma.uiass.eia.pds.persistance.RevisionRepository;
import ma.uiass.eia.pds.persistance.RevisionRepositoryImpl;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import javax.persistence.Transient;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class NFLD extends EtatAmbulance{

    @Transient
    static double A,B;


    @Override
    public void upDate_A_B() {
        RevisionRepository rev = new RevisionRepositoryImpl();

        double test = 0;

        List<Revision> revisions = rev.get_m01_m02_m12_revision().get(1);

        for (Revision elt : revisions) {
            long daysBetween = ChronoUnit.DAYS.between(elt.getStartDate(), elt.getEndDate());
            test += daysBetween;
        }

        NFLD.A = test/revisions.size();
        NFLD.B = NFLD.A+1;
    }


    public double[][] transition_matrix(){

//        double[][] P = {{0.95, 0.04, 0.01},
//                {0.9, 0.1, 0},
//                {0.6, 0, 0.4}};

        this.upDate_A_B();
        this.calcul_X();


        double a1=Math.max((F.A-this.x)/F.B,0);

        double a2=Math.max((NFCD.A-this.x)/NFCD.B,0);

        double a3=Math.max((NFLD.A-this.x)/ NFLD.B,0);

        F f=new F();

        double[][] P = {{a1, (1 - a1) *f.q, (1 - f.q) * (1 - a1)},
                {1 - a2, a2, 0},
                {1 - a3, 0, a3}};

        if(!this.isTransitionMatrix(P)){
            double[][] M = {{0.95, 0.04, 0.01},
                    {0.9, 0.1, 0},
                    {0.6, 0, 0.4}};
            return M;


        }

        return P;
    }




    @Override
    public double predict_Y(){

        double[][] P = transition_matrix();

        double[][] A = {{1, 0, -P[0][2] / (1 - P[0][0])},
                {0, 1, -P[0][1] / (1 - P[0][0])},
                {0, -P[1][0] / (1 - P[1][1]), 1}};

        double[] b = {(P[0][2] + P[0][0]) / (1 - P[0][0]),
                (P[0][0] + P[0][1]) / (1 - P[0][0]),
                (P[1][0] + P[1][1]) / (1 - P[1][1])};

        RealMatrix AMatrix = MatrixUtils.createRealMatrix(A);
        RealVector bVector = MatrixUtils.createRealVector(b);
        RealVector X = MatrixUtils.inverse(AMatrix).operate(bVector);

        System.out.println("m01: " + X.getEntry(0));
        System.out.println("m02: " + X.getEntry(1));
        System.out.println("m12: " + X.getEntry(2));

        return X.getEntry(1);
    }



}
