package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine{
    private final List<Simulation> simulations;
    private final List<Thread> threads = new ArrayList<>();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync(){
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync(){
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }

        awaitTerminationEnd();
    }

    public void runAsyncInThreadPool(){
        for (Simulation simulation : simulations) {
            threadPool.submit(simulation);
        }
        awaitTerminationEnd();
    }

    public void awaitTerminationEnd() {
        try {
            System.out.println("Waiting for threads to terminate...");
            for (Thread thread : threads) {
                thread.join();
            }
            threadPool.shutdown();

            if (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }


        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

