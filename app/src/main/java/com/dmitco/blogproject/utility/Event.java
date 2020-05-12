package com.dmitco.blogproject.utility;

public class Event<T> {
    private boolean hasBeenHandled;
    private final T content;

    public Event(T content) {
        this.content = content;
    }

    public final boolean getHasBeenHandled() {
        return this.hasBeenHandled;
    }
    /**
     * Returns the content and prevents its use again.
     */
    public final T getContentIfNotHandled(){

        if (this.hasBeenHandled) {
            return null;
        } else {
            this.hasBeenHandled = true;
            return content;
        }

    }
    /**
     * Returns the content, even if it's already been handled.
     */
    public final T peekContent() {
        return this.content;
    }
}
