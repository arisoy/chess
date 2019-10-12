package com.mert.chess.ui.component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * Project Edit Component
 *
 * this component is intended to use within the racks visual component, representing the square rack blocks
 * with an occupancy rate and a color code for rack type
 *
 * @author ardarda 06.10.2019
 */
@Tag("my-square-element")
@HtmlImport("bower_components/my-square-element/my-square-element.html")
public class MySquareComponent extends PolymerTemplate<TemplateModel>   {

  @Id("occupancyDiv")
  private Div occupancyDiv;

  public enum Occupancy {

    HUNDRED_PERCENT;

    // rack-block-element file has the style classes named as below, i.e. return values
    static String getCssClass(Occupancy occupancy) {
      switch (occupancy) {
        case HUNDRED_PERCENT:       return "hunderPercent";

        default: return "";
      }
    }
  }

  // disabled block constructor
  public MySquareComponent() {
    occupancyDiv.setClassName(Occupancy.getCssClass(Occupancy.HUNDRED_PERCENT));
    getElement().getStyle().set("border-color", "#000000");
    occupancyDiv.getElement().getStyle().set("background", "#000000");
  }
}
