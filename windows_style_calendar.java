import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class windows_style_calendar extends JFrame {

    private JLabel dateLabel;
    private JLabel timeLabel;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private Point mouseClickPoint;

    public windows_style_calendar() {
        // Configurações da janela
        setTitle("Calendário Estilo Windows");
        setSize(350, 220);
        setUndecorated(true); // Remove a barra de título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurações para bordas arredondadas
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        // Adicionar MouseListener e MouseMotionListener para mover a janela
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseClickPoint = e.getPoint(); // Armazena a posição inicial do clique
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Obtém a nova posição da janela
                int x = e.getXOnScreen() - mouseClickPoint.x;
                int y = e.getYOnScreen() - mouseClickPoint.y;
                setLocation(x, y); // Move a janela para a nova posição
            }
        });

        // Painel de fundo com cor sólida e opacidade
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(245, 245, 245, 230)); // Cor cinza-claro levemente opaca
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bordas arredondadas
            }
        };
        panel.setLayout(new GridLayout(2, 1));

        // Formato da data e hora
        dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy"); // Ex: Sexta-feira, 01 Setembro 2024
        timeFormat = new SimpleDateFormat("HH:mm:ss");

        // Criação dos labels
        dateLabel = new JLabel();
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        dateLabel.setForeground(new Color(50, 50, 50)); // Cinza escuro

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        timeLabel.setForeground(new Color(30, 144, 255)); // Azul claro (similar ao Windows)

        panel.add(dateLabel);
        panel.add(timeLabel);
        add(panel);

        // Atualiza a data e hora inicialmente
        updateDateTime();

        // Timer para atualizar a hora a cada segundo
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDateTime();
            }
        });
        timer.start();
    }

    // Método para atualizar a data e a hora
    private void updateDateTime() {
        Date now = new Date();
        dateLabel.setText(dateFormat.format(now));
        timeLabel.setText(timeFormat.format(now));
    }

    // Método principal para rodar o programa
    public static void main(String[] args) {
        // Certificando que o código Swing roda na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                windows_style_calendar calendar = new windows_style_calendar();
                calendar.setVisible(true);
            }
        });
    }
}
