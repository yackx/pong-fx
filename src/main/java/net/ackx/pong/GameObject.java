package net.ackx.pong;

import javafx.scene.Node;

/**
 * Common ancestor for all game objects.
 *
 * Each instance contains its own state, optionally its own logic,
 * and a reference to a Node.
 */
public class GameObject {
    Node node;

    GameObject(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public boolean isCollidingWith(GameObject other) {
        return node.getBoundsInParent().intersects(other.getNode().getBoundsInParent());
    }

    double getPanelHeight() {
        return node.getParent().getLayoutBounds().getHeight();
    }

    double getPanelWidth() {
        return node.getParent().getLayoutBounds().getWidth();
    }
}
