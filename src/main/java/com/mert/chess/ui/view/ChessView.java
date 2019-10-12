package com.mert.chess.ui.view;

import com.mert.chess.ui.MainAppLayout;

import com.mert.chess.ui.component.MySquareComponent;
import com.mert.chess.ui.component.MySquareComponent.Occupancy;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Chess", layout = MainAppLayout.class)
public class ChessView extends VerticalLayout {

  private VerticalLayout verticalLayout;

  //constructor
  @Autowired
  public ChessView() {

    setSizeFull();
    buildRacks();
    this.add(verticalLayout);

  }

  private void buildRacks() {

    verticalLayout = new VerticalLayout();
    Label mert = new Label("Mert");

    HorizontalLayout horizontalLayout = new HorizontalLayout();
    MySquareComponent mySquareComponent = new MySquareComponent();

    horizontalLayout.add(mySquareComponent,mert);
    verticalLayout.add(horizontalLayout);

  }

  private final List<String> colorList = Arrays.asList(
      "#e6194B",
      "#3cb44b",
      "#ffe119",
      "#4363d8",
      "#f58231",
      "#911eb4",
      "#42d4f4",
      "#f032e6",
      "#bfef45",
      "#fabebe",
      "#469990",
      "#e6beff",
      "#9A6324",
      "#800000",
      "#aaffc3",
      "#808000",
      "#e0b68b",
      "#000075");

}
