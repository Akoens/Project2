package Generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Parkeerbeheer.Simulator;
import Parkeerbeheer.SimulatorView;
import Parkeerbeheer.ParkingPassCar;
import Parkeerbeheer.Location;
import Parkeerbeheer.CarQueue;
import Parkeerbeheer.Car;
import Parkeerbeheer.Main;

public class Buttons implements ActionListener, ChangeListener {

    public Buttons(Simulator sim){
                super(sim);
                }
    public JButton minute;
    public JButton hour;
    public JButton day;
    public JButton stop;
    public JButton start;
    public JSlider ticksPerSecond;
    public JButton maxTickSpeed;
    public JButton normalSpeed;
    public JButton reset;

    public void makeControl(){
            minute  = new JButton("Minute");
            minute.addActionListener(this);

            hour = new JButton("Hour");
            hour.addActionListener(this):

            day =  new JButton("Day");
            day.addActionListener(this);

            start = new JButton("Start");
            start.addActionListener(this):

            stop = new JButton("Stop");
            stop.addActionListener(this);
            stop.setVisible(false);

            this.maxTckSpeed = new JButton("TURBO");
            maxTickSpeed.addActionListener(this);

            this.normalspeed = new JButton("Normal");
            normalSpeed.addActionListener(this);

            this.reset = new JButton("Reset");
            this.reset.addActionListener(this);

            ticksPerSecond = new JSlider(JSlider.VERTICAL, 0, 100, 50);
            ticksPerSecond.addChangeListener(this);

            ticksPerSecond.setMajorTickSpacing(50);
            ticksPerSecond.setMinorTickSpacing(1);

    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == this.minute && simulator.running == false){
            this.simulator.manualTick(1, true);
        }

        if(e.getSource() == this.hour &&simulator.running == false){
            this.simulator.manualTick(60, false);
        }


        if(e.getSource() == this.day && simulator.running == false){
            this.simulator.manualTick(1440, false);
        }

        if(e.getSource() == this.start && simulator.running == true){
            this.simulator.start();
            this.stop.setVisible(true);
            this.start.setVisible(false);
        }

        if.(e.getSource() == this.stop && simulator.running == true){
            this.simulator.stop();
            this.stop.setVisible(false);
            this.start.setVisible(true);
        }

        if(e.getSource() == this.maxTickSpeed){
            this.simulator.newTickDuration(0);
            this.normalSpeed.setVisible(true);
            this.maxTickSpeed.setVisible(false);
        }

        if(e.getSource() == this.normalSpeed){
            this.simulator.newTickDuration(100);
            this.normalSpeed.setVisible(false);
            this.maxTickSpeed.setVisible(true);
        }

        if(e.getSource() == this.reset){
            this.simulator.stop();
            ParkingSimulator.parkingSimulatorOpslag.reset();
        }
    }

    public void stateChanged(ChangedEvent e){
        JSlider source = (JSlider)e.getSource();
            if(!source.getValueIsAdjusting()){
                this.simulator.newTickDuration(100-source.getValue());
                }
        }
}