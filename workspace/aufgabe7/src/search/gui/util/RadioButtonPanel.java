/*
 * RadioButtonPanel.java
 * Created on 24.04.2003
 */
package search.gui.util;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 * This class provides an example how standard dialog forms can
 * be enhanced with special customized panels.
 * <p>
 * The purpose of class <code>RadioButtonPanel</code> is to provide
 * a very simple radio button functionality the is generalized in
 * such a way that it will serve most purposes.
 * <p>
 * Features are a very simple constructor, the optional facility to
 * supply an ActionListener that will automatically be connected
 * with each button and a simplified mechanism to query the button
 * selection.
 * <p>
 * The panel will be surrounded by a <code>TitledBorder</code>. 
 * 
 * @author Erich Ehses
 */
@SuppressWarnings("serial")
public final class RadioButtonPanel extends JPanel implements ActionListener {
    private ButtonGroup group = new ButtonGroup();
    private JRadioButton[] items;
    private ActionListener myActionListener;

    /**
     * An untitled group of radio buttens will be created.
     * The initial selection is set to the first item.
     * @param buttonNames names of the radio buttons
     */
    public RadioButtonPanel(String[] buttonNames) {
        this("", buttonNames, 0);
    }

    /**
     * An untitled group of radio buttens will be created.
     * @param buttonNames names of the radio buttons
     * @param initialSelected index of initially selected button
     */
    public RadioButtonPanel(String[] buttonNames, int initialSelected) {
        this("", buttonNames, initialSelected);
    }

    /**
     * A titled group of radio buttens will be created.
     * The initial selection is set to the first item.
     * @param title title of the button panel
     * @param buttonNames names of the radio buttons
     */
    public RadioButtonPanel(String title, String[] buttonNames) {
        this(title, buttonNames, 0);
    }

    /**
     * A titled group of radio buttens will be created.
     * @param title title of the button panel
     * @param buttonNames names of the radio buttons
     * @param selected index of initially selected button
     */
    public RadioButtonPanel(String title, String[] buttonNames, int selected) {
        // set layout and draw border
        super(new GridBagLayout());
        setBorder(new TitledBorder(title));

        items = new JRadioButton[buttonNames.length];

        // define grid bag constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        // create and place all radio buttons
        for (int i = 0; i < items.length; i++) {
            items[i] = new JRadioButton(buttonNames[i], selected == i);
            items[i].addActionListener(this);
            group.add(items[i]);
            add(items[i], constraints);
        }

        // create an extra invisible label that will
        // take up screens space in case the panel area
        // is larger than needed.
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 99;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.VERTICAL;
        JLabel verticalFillLabel = new JLabel();
        add(verticalFillLabel, constraints);
    }

    /**
     * Returns the index of the selected button
     * @return index of selected button
     */
    public int getSelectedIndex() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].isSelected())
                return i;
        }
        throw new IllegalStateException();
    }

    /**
     * Preselect the selection.
     * This method can safely be called from any thread.
     * @param index index of selected button
     */
    public void setSelectedIndex(final int index) {
        // invokeLater is used in order to demonstrate
        // how to achieve that changes at swing widgets
        // are postponed until they may be executed within
        // the event thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                items[index].setSelected(true);
            }
        });
    }

    /**
     * Returns the name of the selected button.
     * The result is cast to <code>Object</code> in order
     * to stay in line with similar methods in the library.
     * (may be a corresponding interface should be implemented).
     * @return Object String representation of selected button.
     */
    public Object getSelectedObject() {
        return items[getSelectedIndex()].getText();
    }

    /**
     * Sets the selection.
     * This method can safely be called from any thread.
     * @param select
     * @throws IllegalArgumentException if argument is unusable
     */
    public void setSelectedObject(Object select) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getText().equals(select)) {
                setSelectedIndex(i);
                return;
            }
        }
        throw new IllegalArgumentException(select + " is not supported");
    }

    /**
     * Register an <code>ActionListener</code>
     * The method <code>actionPerformed</code> will be called for
     * each selection action.
     * @param listener action listener object
     */
    public void addActionListener(ActionListener listener) {
        myActionListener = listener;
    }

    /**
     * Callback method for all buttons. If an action listener is registered, it
     * will forward all action events to this listener.
     * 
     * @param e event that triggered the action.
     */
    public void actionPerformed(ActionEvent e) {
        if (myActionListener != null) {
            myActionListener.actionPerformed(e);
        }
    }
}
