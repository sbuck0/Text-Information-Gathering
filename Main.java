package wordcounter;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;


public class Main implements ActionListener{
	
	private static JLabel label;
	private static JTextArea entry;
	private static JComboBox<String> cb;
	private static JButton btn;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Article Information");
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(700, 800);

	    JPanel panel = new JPanel(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();

	    frame.add(panel);
	    
	    label = new JLabel("Place the text of interest in the text box below and select one of the possible events and click Continue");
	    label.setAlignmentX(Component.CENTER_ALIGNMENT);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.ipadx = 20;
	    c.ipady = 50;
	    panel.add(label, c);
	    
	    entry = new JTextArea();
	    entry.setAlignmentX(Component.CENTER_ALIGNMENT);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.ipadx = 20;
	    c.ipady = 400;
	    panel.add(entry, c);

	    String[] choices = { "Amount of words", "What word appears most", "Word Finder and Count" };

	    cb = new JComboBox<String>(choices);
	    cb.setMaximumSize(cb.getPreferredSize());
	    cb.setAlignmentX(Component.CENTER_ALIGNMENT);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx= 0;
	    c.gridy = 10;
	    c.ipadx = 20;
	    c.ipady = 5;
	    panel.add(cb, c);

	    btn = new JButton("Continue");
	    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 11;
	    c.ipadx = 0;
	    btn.addActionListener(new Main());
	    panel.add(btn,c);
	    frame.setVisible(true);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String choice = (String) cb.getSelectedItem();
		String url = entry.getText();
		String text = url.toString();
		int beginning = 0;
		int ending = 0;
		boolean first = false;
		boolean letter = false;
		TreeMap<String, Integer> x = new TreeMap<>();
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			if (Character.isLetter(text.charAt(i))) {
				letter = true;
				if (first == false) {
					beginning = i;
					first = true;
				}
			} else if (!Character.isLetter(text.charAt(i))) {
				if (letter == true) {
					letter = false;
					ending = i;
					String t = text.substring(beginning, ending);
					if (!x.containsKey(t)) {
						x.put(t, 1);
					} else {
						int z = x.get(t);
						z++;
						x.put(t, z);
					}
					beginning = i;
				}
			}
		}
		if (choice.equals("Amount of words")) {
			if (x.isEmpty()) {
				JOptionPane.showMessageDialog(entry, "There are no words in this documebnt");
			}
			int sum = 0;
			ArrayList<String> number = new ArrayList<>();
			Set<String> set = x.keySet();
			for (String b: set) {
				number.add(b);
			}
			for(String a: number) {
				sum += x.get(a);
			}
			JOptionPane.showMessageDialog(entry, "There are " + sum + " words in the text that you gave.");
		} else if (choice.equals("What word appears most")) {
			if (x.isEmpty()) {
				JOptionPane.showMessageDialog(entry, "There are no words in this documebnt");
			}
			ArrayList<String> number = new ArrayList<>();
			Set<String> set = x.keySet();
			for (String b: set) {
				number.add(b);
			}
			int numberofTimes = 0;
			String most = "";
			for (String a: number) {
				if (x.get(a) > numberofTimes) {
					numberofTimes = x.get(a);
					most = a;
				}
			}
			JOptionPane.showMessageDialog(entry, most + " showed up the most at " + numberofTimes + " times.");
		} else {
			String name = JOptionPane.showInputDialog("What word do you want to look for?");
			if (!x.containsKey(name)) {
				JOptionPane.showMessageDialog(entry, "There is no such word in this document");
			} else {
				int num = x.get(name);
				JOptionPane.showMessageDialog(entry, "The word " + name + " appears " + num + " number of times.");
			}
		}
		
	}
}
