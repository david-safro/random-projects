import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mandelbrot extends JPanel {
    private int width = 800;
    private int height = 800;
    private int maxIterations = 2000; // Increased max iterations
    private double zoom = 500.0; // Increased zoom range
    private double xCenter = -0.7;
    private double yCenter = 0.0;
    private double mouseX = 0.0;
    private double mouseY = 0.0;

    public Mandelbrot() {
        setPreferredSize(new Dimension(width, height));

        JButton saveButton = new JButton("Save Image (PNG)");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImage();
            }
        });

        JSlider iterationsSlider = new JSlider(1, 2000, maxIterations);
        iterationsSlider.addChangeListener(e -> {
            maxIterations = iterationsSlider.getValue();
            repaint();
        });

        JSlider zoomSlider = new JSlider(1, 1000, (int) zoom);
        zoomSlider.addChangeListener(e -> {
            zoom = zoomSlider.getValue();
            repaint();
        });

        setLayout(new BorderLayout());
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout());
        controlsPanel.add(saveButton);
        controlsPanel.add(new JLabel("Max Iterations:"));
        controlsPanel.add(iterationsSlider);
        controlsPanel.add(new JLabel("Zoom:"));
        controlsPanel.add(zoomSlider);
        add(controlsPanel, BorderLayout.SOUTH);

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                zoom += e.getUnitsToScroll() * 10;
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        yCenter -= 0.1 / zoom;
                        break;
                    case KeyEvent.VK_DOWN:
                        yCenter += 0.1 / zoom;
                        break;
                    case KeyEvent.VK_LEFT:
                        xCenter -= 0.1 / zoom;
                        break;
                    case KeyEvent.VK_RIGHT:
                        xCenter += 0.1 / zoom;
                        break;
                }
                repaint();
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double zx, zy, cX, cY;
                zx = zy = 0;
                cX = (x - width / 2.0) / zoom + xCenter;
                cY = (y - height / 2.0) / zoom + yCenter;
                int iteration = 0;

                while (zx * zx + zy * zy < 4 && iteration < maxIterations) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iteration++;
                }

                if (iteration < maxIterations) {
                    int color = Color.HSBtoRGB(iteration / 256f, 1, iteration / (iteration + 8f));
                    image.setRGB(x, y, color);
                } else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }

        g.drawImage(image, 0, 0, this);
    }

    private void saveImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        paintComponent(image.getGraphics());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Mandelbrot Image");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String fileName = fileToSave.getAbsolutePath();
            if (!fileName.toLowerCase().endsWith(".png")) {
                fileName += ".png";
            }
            File saveFile = new File(fileName);

            try {
                ImageIO.write(image, "png", saveFile);
                System.out.println("Image saved as " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
