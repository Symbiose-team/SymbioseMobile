/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.symbiose.GestionUsers.gui;

import com.symbiose.GestionUsers.entities.User;
import com.symbiose.GestionUsers.gui.settingsForm;
import com.symbiose.GestionUsers.services.userService;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.symbiose.Utils.SideMenuBaseForm;
import com.codename1.ui.Button;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;

/**
 *
 * @author SkanderThabet
 */
public class Dashboard extends SideMenuBaseForm {

    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Remaining tasks", "Effort"};
    userService u = new userService();

    public Dashboard(Resources res) {

        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        User us = u.profile();
        String image = us.getPicture();

        EncodedImage placeholder = EncodedImage.createFromImage(res.getImage("Image6.png"), false);

        URLImage profilePic = URLImage.createToStorage(placeholder, "http://localhost/Java/SymbioseApi/Symbiose-WEB/Symbiose/web/uploads/images/" + image,
                "http://localhost/Java/SymbioseApi/Symbiose-WEB/Symbiose/web/uploads/images/" + image);

        ImageViewer img = new ImageViewer(profilePic);
        Image mask = res.getImage("round-mask.png");
        //profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        settingsButton.addActionListener((evt) -> {
            new settingsForm(res).show();
        });

        Container remainingTasks = BoxLayout.encloseY(
                new Label("12", "CenterTitle"),
                new Label("Produits", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("32", "CenterTitle"),
                new Label("Teams", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");
        String name = us.getFirst_name();
        String lastname = us.getLast_name();
        String role = us.getGenre();

//        Label space = new Label("", "TitlePictureSpace");
//        space.setShowEvenIfBlank(true);
        Container titleCmp
                = BoxLayout.encloseY(
                        BorderLayout.north(BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                        new Label(name + " " + lastname, "Title"),
                                        new Label(role, "SubTitle")
                                )
                        ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
       // titleCmp.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleCmp);

//        Label separator = new Label("", "BlueSeparatorLine");
//        separator.setShowEvenIfBlank(true);
//        add(BorderLayout.NORTH, separator);

//        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
//
//        addButtonBottom(arrowDown, "Finish landing page concept", 0xd997f1, true);
//        addButtonBottom(arrowDown, "Design app illustrations", 0x5ae29d, false);
//        addButtonBottom(arrowDown, "Javascript training ", 0x4dc2ff, false);
//        addButtonBottom(arrowDown, "Surprise Party for Matt", 0xffc06f, false);

        XYMultipleSeriesDataset multi = new XYMultipleSeriesDataset();

        XYSeries seriesXY = new XYSeries("AAA", 0);
        multi.addSeries(seriesXY);
        seriesXY.add(3, 3);
        seriesXY.add(4, 4);
        seriesXY.add(5, 5);
        seriesXY.add(6, 4);
        seriesXY.add(7, 6);
        seriesXY.add(8, 7);

        seriesXY = new XYSeries("BBB", 0);
        multi.addSeries(seriesXY);
        seriesXY.add(3, 7);
        seriesXY.add(4, 6);
        seriesXY.add(5, 3);
        seriesXY.add(6, 2);
        seriesXY.add(7, 1);
        seriesXY.add(8, 0);

        XYMultipleSeriesRenderer renderer = createChartMultiRenderer();

        CubicLineChart chart = new CubicLineChart(multi, renderer,
                0.5f);

        Container enclosure = BorderLayout.south(new ChartComponent(chart)).
                add(BorderLayout.NORTH, FlowLayout.encloseCenter(
                        new Label("Burndown Chart :"),
                        new Label(LABELS[1], colorCircle(COLORS[0])),
                        new Label(LABELS[0], colorCircle(COLORS[1]))
                ));

        add(BorderLayout.SOUTH,
                enclosure);
//ProjectStatusChart p = new ProjectStatusChart();
//add(BorderLayout.CENTER,p.execute());
        setupSideMenu(res);
    }

    private Image colorCircle(int color) {
        int size = Display.getInstance().convertToPixels(3);
        Image i = Image.createImage(size, size, 0);
        Graphics g = i.getGraphics();
        g.setColor(color);
        g.fillArc(0, 0, size, size, 0, 360);
        return i;
    }
//    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
//        MultiButton finishLandingPage = new MultiButton(text);
//        finishLandingPage.setEmblem(arrowDown);
//        finishLandingPage.setUIID("Container");
//        finishLandingPage.setUIIDLine1("TodayEntry");
//        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
//        finishLandingPage.setIconUIID("Container");
//        this.add(BorderLayout.NORTH,FlowLayout.encloseIn(finishLandingPage));
//    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 4 - height / 2, height / 6, height / 4, height / 2, 0, 360);
        return img;
    }

    private XYMultipleSeriesRenderer createChartMultiRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        for (int color : COLORS) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            r.setFillPoints(false);
            XYSeriesRenderer.FillOutsideLine outline = new XYSeriesRenderer.FillOutsideLine(XYSeriesRenderer.FillOutsideLine.Type.BELOW);
            outline.setColor(color);
            r.addFillOutsideLine(outline);
            r.setLineWidth(5);
        }
        renderer.setPointSize(5f);
        renderer.setLabelsColor(0);
        renderer.setBackgroundColor(0xffffffff);
        renderer.setApplyBackgroundColor(true);
        renderer.setAxesColor(COLORS[0]);

        renderer.setXTitle("");
        renderer.setYTitle("");
        renderer.setAxesColor(0xcccccc);
        renderer.setLabelsColor(0);
        renderer.setXLabels(5);
        renderer.setYLabels(5);
        renderer.setShowGrid(true);

        renderer.setMargins(new int[]{0, 0, 0, 0});
        renderer.setMarginsColor(0xffffff);

        renderer.setShowLegend(false);

        renderer.setXAxisMin(3);
        renderer.setXAxisMax(8);
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(10);
        return renderer;
    }

}
