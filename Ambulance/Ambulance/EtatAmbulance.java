package ma.uiass.eia.pds.model.Ambulance;


import ma.uiass.eia.pds.persistance.RevisionRepository;
import ma.uiass.eia.pds.persistance.RevisionRepositoryImpl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public abstract class EtatAmbulance {

    Ambulance ambulance;


    double x,y;



    public EtatAmbulance(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public EtatAmbulance() {

    }

    public void setAmbulance(Ambulance ambulance){
        this.ambulance=ambulance;
    }

    public abstract void upDate_A_B();

    public LocalDate last_Revision(){
        RevisionRepository dd=new RevisionRepositoryImpl();

        return dd.get_last_revision_by_ambulance(this.ambulance).getEndDate();

    }

    public void calcul_X(){

        double duration = ChronoUnit.DAYS.between(LocalDate.now(), this.last_Revision());

//        double days = duration.toDays();
//        double hours = duration.toHours() % 24;
//        this.x=hours;
//        double minutes = duration.toMinutes() % 60;

//        List<Double> timeDifference = new ArrayList<>();
//        timeDifference.add(days);
        this.x=duration;
//        timeDifference.add(hours);
//        timeDifference.add(minutes);
    }


    public abstract double predict_Y();


    public boolean isTransitionMatrix(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        // Check if the matrix is square
        if (rows != columns) {
            return false;
        }

        // Check if all elements are non-negative
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] < 0) {
                    return false;
                }
            }
        }

        // Check if each row sums up to 1
        for (int i = 0; i < rows; i++) {
            double rowSum = 0;
            for (int j = 0; j < columns; j++) {
                rowSum += matrix[i][j];
            }
            if (Math.abs(rowSum - 1) > 0.000001) {
                return false;
            }
        }

        return true;
    }







    ////  persistance





}