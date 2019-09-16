public class Elevator{

    private static final int NUMBEROFFLOORS=7;
    private int currentFloor = 0;
    private boolean goingUp = true;
    private int[] numberOfPassengersInAFloor = new int[]{0, 0, 0, 0, 0, 0, 0, 0};


    public void move(){

        if(getCurrentFloor() == NUMBEROFFLOORS){
            setGoingUp(false);
        }
        else if(getCurrentFloor() == 1){
            setGoingUp(true);
        }

        int passengersWhoLeftElevator;

        if(isGoingUp()){
            setCurrentFloor(getCurrentFloor() + 1);
            passengersWhoLeftElevator = this.numberOfPassengersInAFloor[getCurrentFloor()] -
                    this.numberOfPassengersInAFloor[getCurrentFloor() - 1];
            resetNumberOfPassengers(passengersWhoLeftElevator, isGoingUp());
        }
        else {
            setCurrentFloor(getCurrentFloor() - 1);
            passengersWhoLeftElevator = this.numberOfPassengersInAFloor[getCurrentFloor()] -
                    this.numberOfPassengersInAFloor[getCurrentFloor() + 1];
            resetNumberOfPassengers(passengersWhoLeftElevator, isGoingUp());
        }

        System.out.println(toString());

    }

    public void resetNumberOfPassengers(int passengersWhoLeftElevator, boolean goingUp){
        if(goingUp){
            for(int i = 1; i < getCurrentFloor(); i++){
                this.numberOfPassengersInAFloor[i] -= passengersWhoLeftElevator;
            }
        } else {
            for(int l = NUMBEROFFLOORS - 1; l > getCurrentFloor(); l--){
                this.numberOfPassengersInAFloor[l] -= passengersWhoLeftElevator;
            }
        }
    }

    public void boardPassenger(int destinationFloor){
        if(isGoingUp()){
            incrementPassengersGoingUp(destinationFloor);
        } else {
            incrementPassengersGoingDown(destinationFloor);
        }
    }

    public void incrementPassengersGoingUp(int destinationFloor){
        int floorsToGoUp = getCurrentFloor();

        while(floorsToGoUp < destinationFloor){
            this.numberOfPassengersInAFloor[floorsToGoUp] += 1;
            floorsToGoUp++;
        }
    }

    public void incrementPassengersGoingDown(int destinationFloor){
        int floorsToGoDown = getCurrentFloor();

        while(floorsToGoDown > destinationFloor){
            this.numberOfPassengersInAFloor[floorsToGoDown] += 1;
            floorsToGoDown--;
        }
    }

    public static void main(String [] args){

        //For testing only, main will be removed

        Elevator elevator = new Elevator();
        elevator.boardPassenger(3);
        elevator.boardPassenger(3);
        elevator.boardPassenger(5);

        for(int i = 0; i < 13; i++){
            elevator.move();
        }

        elevator.toString();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public int [] getNumberOfPassengersInAFloor() {
        return numberOfPassengersInAFloor;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Elevator{" +
                "Floor=" + (getCurrentFloor()) +
                ", With Number Of Passengers =" + this.numberOfPassengersInAFloor[getCurrentFloor()] +
                '}';
    }
}