package nl.utwente.viskell.ui.components;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import nl.utwente.viskell.haskell.expr.*;
import nl.utwente.viskell.haskell.type.*;
import nl.utwente.viskell.ui.ToplevelPane;

public class ConstantMatchBlock extends Block {
    
    /** The original type of the value.  */
    private final Type baseType;
    
    /** The space containing the input anchor. */
    private final VBox inputSpace;
    
    /** The input anchor of this block. */
    private final InputAnchor input;
    
    /** The label containing the type of this block */
    private final Label inputType;

    /** The label for placing the value of this block. */
    private final Label value;

    public ConstantMatchBlock(ToplevelPane pane, Type type, String val) {
        super(pane);
        this.baseType = type;
        this.value = new Label(val);
        this.input = new InputAnchor(this);
        this.input.setFreshRequiredType(this.baseType, new TypeScope());
        this.inputType = new Label(this.input.getStringType());
        this.inputType.getStyleClass().add("inputType");
        this.value.setAlignment(Pos.CENTER);
        this.value.getStyleClass().add("content");
        
        BorderPane body = new BorderPane();
        body.setPickOnBounds(false);
        body.setCenter(this.value);
        
        this.inputSpace = new VBox(this.input, this.inputType);
        this.inputSpace.setAlignment(Pos.CENTER);
        this.inputSpace.setPickOnBounds(false);
        body.setTop(this.inputSpace);
       
        body.getStyleClass().addAll("block", "constantMatch");
        this.getChildren().add(body);
    }

    public final String getValue() {
        return this.value.getText();
    }
    
    public final Type getAnnotationType() {
        return this.baseType.getFresh();
    }
    
    @Override
    public List<InputAnchor> getAllInputs() {
        return ImmutableList.of(this.input);
    }

    @Override
    public List<OutputAnchor> getAllOutputs() {
        return ImmutableList.of();
    }

    @Override
    protected void refreshAnchorTypes() {
        this.input.setFreshRequiredType(this.baseType, new TypeScope());
        this.inputSpace.setTranslateY(this.input.hasConnection() ? 9 : 0);
        this.inputType.setVisible(! this.input.hasConnection());
    }

    @Override
    public Expression getLocalExpr(Set<OutputAnchor> outsideAnchors) {
        return this.input.getLocalExpr(outsideAnchors);
    }

    @Override
    public void invalidateVisualState() {
        this.input.invalidateVisualState();
        this.inputType.setText(this.input.getStringType());
    }

    @Override
    public Optional<Block> getNewCopy() {
        return Optional.of(new ConstantMatchBlock(this.getToplevel(), this.baseType.getFresh(), this.value.getText()));
    }

}
