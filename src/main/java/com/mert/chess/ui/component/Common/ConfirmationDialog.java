package com.mert.chess.ui.component.Common;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

/**
 * A generic dialog for confirming or cancelling an action.
 *
 * @param <T> The type of the action's subject
 */
public class ConfirmationDialog<T extends Serializable> extends Dialog {

	private final H3 titleField = new H3();
	private final Div messageLabel = new Div();
	private final Div extraMessageLabel = new Div();
	private final Button confirmButton = new Button();
	private final Button cancelButton = new Button("Cancel");
	private Registration registrationForConfirm;
	private Registration registrationForCancel;

	/**
	 * Constructor.
	 */
	public ConfirmationDialog() {
		setCloseOnEsc(true);
		setCloseOnOutsideClick(false);

		confirmButton.addClickListener(e -> close());
		confirmButton.getElement().setAttribute("theme", "tertiary");
		confirmButton.setAutofocus(true);
		cancelButton.addClickListener(e -> close());
		cancelButton.getElement().setAttribute("theme", "tertiary");

		HorizontalLayout buttonBar = new HorizontalLayout(confirmButton, cancelButton);
		buttonBar.setClassName("buttons confirm-buttons");

		Div labels = new Div(messageLabel, extraMessageLabel);
		labels.setClassName("confirm-text");

		titleField.setClassName("confirm-title");

		add(titleField, labels, buttonBar);
	}

	/**
	 * Opens the confirmation dialog.
	 *
	 * The dialog will display the given title and message(s), then call
	 * <code>confirmHandler</code> if the Confirm button is clicked, or
	 * <code>cancelHandler</code> if the Cancel button is clicked.
	 *
	 * @param title             The title text
	 * @param message           Detail message (optional, may be empty)
	 * @param additionalMessage Additional message (optional, may be empty)
	 * @param actionName        The action name to be shown on the Confirm button
	 * @param cancelTitle       The cancel action name to be shown on the Confirm
	 *                          button defaults to "Cancel" if null is passed
	 * @param isDisruptive      True if the action is disruptive, such as deleting
	 *                          an item
	 * @param item              The subject of the action
	 * @param confirmHandler    The confirmation handler function
	 * @param cancelHandler     The cancellation handler function
	 */
	public void open(
      String title, String message, String additionalMessage, String actionName, String cancelTitle,
			boolean isDisruptive, T item, Consumer<T> confirmHandler, Runnable cancelHandler) {
		titleField.setText(title);
		if (cancelTitle != null) {
			cancelButton.setText(cancelTitle);
		}
		messageLabel.setText(message);
		extraMessageLabel.setText(additionalMessage);
		confirmButton.setText(actionName);

		if (registrationForConfirm != null) {
			registrationForConfirm.remove();
		}
		registrationForConfirm = confirmButton.addClickListener(e -> 
		confirmHandler.accept(item));
		if (registrationForCancel != null) {
			registrationForCancel.remove();
		}
		registrationForCancel = cancelButton.addClickListener(e -> 
		cancelHandler.run());
		this.addOpenedChangeListener(e -> {
			if (!e.isOpened()) {
				cancelHandler.run();
			}
		});
		if (isDisruptive) {
			confirmButton.getElement().setAttribute("theme", "tertiary error");
		}
		open();
	}
	
	public void open(String title, String message, String actionName, String cancelTitle,
			boolean isDisruptive, List<T> items, Consumer<List<T>> confirmHandler, Runnable cancelHandler) {
		titleField.setText(title);
		if (cancelTitle != null) {
			cancelButton.setText(cancelTitle);
		}
		messageLabel.setText(message);
		confirmButton.setText(actionName);

		if (registrationForConfirm != null) {
			registrationForConfirm.remove();
		}
		registrationForConfirm = confirmButton.addClickListener(e -> 
		confirmHandler.accept(items));
		if (registrationForCancel != null) {
			registrationForCancel.remove();
		}
		registrationForCancel = cancelButton.addClickListener(e -> 
		cancelHandler.run());
		this.addOpenedChangeListener(e -> {
			if (!e.isOpened()) {
				cancelHandler.run();
			}
		});
		if (isDisruptive) {
			confirmButton.getElement().setAttribute("theme", "tertiary error");
		}
		open();
	}
}
