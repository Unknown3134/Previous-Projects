package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import slotmachine.controller.CloseDialogListener;
import slotmachine.controller.KeyListener;

/**
 * About Dialog is instantiated on program open. The Menu bar makes it visible.
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	public AboutDialog(JFrame parent) {
		super(parent, true);

		setLayout(new GridLayout(3, 2));
		setTitle("About Me");
		setSize(new Dimension(800, 200));

		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton("OK");
		JPanel textPanel = new JPanel();

		add(textPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(ok, BorderLayout.CENTER);
		JLabel studentNo = new JLabel("Student Number: s3791201");
		JLabel name = new JLabel("Name: Paul McKercher");
		JLabel iconCredits = new JLabel(
				"Icons sourced from iconarchive.com, icons8.com. iconsmind.com, designcontest.com, robinweatherall.co.uk");
		studentNo.setFont(new Font("Verdana", 2, 20));
		name.setFont(new Font("Verdana", 2, 20));
		textPanel.add(name);
		textPanel.add(studentNo);
		textPanel.add(iconCredits);
		ok.addActionListener(new CloseDialogListener(this));
		ok.addKeyListener(new KeyListener(this));

	}

}
