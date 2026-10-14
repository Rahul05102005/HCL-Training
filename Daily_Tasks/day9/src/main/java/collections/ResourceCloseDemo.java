package collections;

import java.util.ArrayList;
import java.util.List;

/** Shows that try-with-resources closes resources in reverse initialization order. */
public final class ResourceCloseDemo {
    private ResourceCloseDemo() { }

    public static List<String> closeOrder() {
        List<String> events = new ArrayList<>();
        try (NamedResource first = new NamedResource("first", events);
             NamedResource second = new NamedResource("second", events)) {
            events.add("body");
        }
        return List.copyOf(events);
    }

    private static final class NamedResource implements AutoCloseable {
        private final String name;
        private final List<String> events;
        private NamedResource(String name, List<String> events) { this.name = name; this.events = events; }
        @Override public void close() { events.add("close " + name); }
    }
}
