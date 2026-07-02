package com.zh.courseeq.gui;

import com.zh.courseeq.model.CourseEquivalency;
import com.zh.courseeq.service.SearchMode;
import com.zh.courseeq.service.SearchService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

/** Simple Swing GUI for searching course equivalencies. */
public class EquivalencyFrame extends JFrame {
    private final SearchService service;
    private final JTextField searchField;
    private final JComboBox<String> searchModeBox;
    private final DefaultTableModel tableModel;
    private final JLabel statusLabel;

    public EquivalencyFrame(SearchService service) {
        super("UW <-> BC Course Equivalence App");
        this.service = service;
        this.searchField = new JTextField(28);
        this.searchModeBox = new JComboBox<>(new String[]{"UW Course", "BC Course", "Keyword"});
        this.tableModel = new DefaultTableModel(
                new String[]{"UW Course", "BC Equivalent", "Comments", "Begin Date", "End Date"},
                0
        );
        this.statusLabel = new JLabel("Loaded " + service.count() + " records.", SwingConstants.LEFT);

        setupFrame();
        refreshTable(service.findAll());
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1050, 650));
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 6, 12));
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchModeBox);

        JButton searchButton = new JButton("Search");
        JButton resetButton = new JButton("Show All");
        topPanel.add(searchButton);
        topPanel.add(resetButton);

        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(24);
        table.getColumnModel().getColumn(0).setPreferredWidth(260);
        table.getColumnModel().getColumn(1).setPreferredWidth(260);
        table.getColumnModel().getColumn(2).setPreferredWidth(240);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(6, 12, 12, 12));
        bottomPanel.add(statusLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(event -> performSearch());
        searchField.addActionListener(event -> performSearch());
        resetButton.addActionListener(event -> {
            searchField.setText("");
            refreshTable(service.findAll());
        });
    }

    private void performSearch() {
        String query = searchField.getText();
        SearchMode mode = getSelectedMode();
        List<CourseEquivalency> results = service.search(query, mode);
        refreshTable(results);
    }

    private SearchMode getSelectedMode() {
        int index = searchModeBox.getSelectedIndex();
        return switch (index) {
            case 0 -> SearchMode.UW_COURSE;
            case 1 -> SearchMode.BC_COURSE;
            default -> SearchMode.KEYWORD;
        };
    }

    private void refreshTable(List<CourseEquivalency> records) {
        tableModel.setRowCount(0);
        for (CourseEquivalency record : records) {
            tableModel.addRow(new Object[]{
                    record.getTransferCourse(),
                    record.getEquivalentCourse(),
                    record.getComments(),
                    record.getBeginDate(),
                    record.getEndDate()
            });
        }
        statusLabel.setText("Showing " + records.size() + " record(s).");
    }
}
