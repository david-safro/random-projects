import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        setTitle("Mandelbrot Set");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Mandelbrot mandelbrotPanel = new Mandelbrot();
        add(mandelbrotPanel);

        pack();
    }
}
