package com.mert.chess.ui.component.Common;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.shared.Registration;
import java.util.ArrayList;
import java.util.List;

public class ComboboxComponent extends HorizontalLayout {
  private ComboBox<Role> roleComboBox;
  private ComboBox<User> userComboBox;
  private Binder<Role> roleBinder = new Binder<>();
  private Binder<User> userBinder;
  private List<Role> roleComboboxList;
  private List<User> userComboBoxList;
  private RoleService roleService;
  private UserService userService;
  private ListDataProvider<Role> roleComboBoxListDataProvider;
  private ListDataProvider<User> userComboBoxListDataProvider;

  public List<Role> getRoleComboboxList(){

    return roleComboboxList;
  }

  public List<User> getUserComboBoxList(){

    return userComboBoxList;
  }

  public ComboBox<Role> getRoleComboBox(){

    return roleComboBox;
  }

  public ComboBox<User> getUserComboBox(){

    return userComboBox;
  }


  public ComboboxComponent(RoleService roleService, UserService userService){

    this.roleService = roleService;
    this.userService = userService;

    roleComboBox = new ComboBox<>("Role");
    userComboBox = new ComboBox<>("User");
    roleComboboxList = new ArrayList<>();
    roleComboboxList.addAll(roleService.findAll());
    roleComboBoxListDataProvider = new ListDataProvider<>(roleComboboxList);
    roleComboBox.setDataProvider(roleComboBoxListDataProvider);
    roleComboBox.setItemLabelGenerator(role-> role.getRole());

    addUserToUserComboBox();

    roleComboBox.addValueChangeListener(e -> {

        if (roleComboBox.getValue() == null) {
//          userComboBoxList.clear();
//          User emptyUser = new User();
//          userComboBoxList.add(emptyUser);
          userComboBoxListDataProvider = new ListDataProvider<>(userComboBoxList);
          userComboBoxListDataProvider.refreshAll();
          userComboBox.setEnabled(false);

        }
        else {
          userComboBoxList.clear();
          userComboBoxListDataProvider = new ListDataProvider<>(userComboBoxList);
          userComboBoxListDataProvider.refreshAll();
          userComboBox.setEnabled(true);
          userComboBoxList.addAll(userService.findUserByRole(roleComboBox.getValue().getPk()));
          userComboBoxListDataProvider.refreshAll();
          userComboBox.setDataProvider(userComboBoxListDataProvider);
          userComboBox.setItemLabelGenerator((user -> user.getName()));
        }
      });

    add(roleComboBox, userComboBox);

  }

  public Registration addUserComboBoxSelectionListener(
      HasValue.ValueChangeListener<ComponentValueChangeEvent<ComboBox<User>, User>> listener){
    return (userComboBox.addValueChangeListener(listener));
  }

  private void addUserToUserComboBox() {
    userComboBoxList = new ArrayList<>();
    //trys




    if (roleComboBox.getValue() != null) {
      userComboBoxList.addAll(userService.findUserByRole(roleComboBox.getValue().getPk()));

    }

    if (userComboBoxList.isEmpty())
      userComboBox.setEnabled(false);
    else {
      userComboBox.setItemLabelGenerator(User::getName);
      userComboBox.setAllowCustomValue(false);
      userComboBox.setItems(userComboBoxList);
      userComboBox.setRequired(true);

    }
  }
}
