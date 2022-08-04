public class Agent {


    public Agent() {

    }

    public Agent(double fitness, double[] position) {
        this.fitness = fitness;
        this.position = position;
    }

    public int dim;


    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }

    public double fitness;

    public double[] position;

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
}
