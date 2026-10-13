package collections;

import java.util.HashSet;
import java.util.Set;

/** Isolated reproduction of equals() without a corresponding hashCode(). */
public final class HashCodeContractDemo {
    private HashCodeContractDemo() { }

    public static boolean run() {
        Set<BrokenEmployee> employees = new HashSet<>();
        employees.add(new BrokenEmployee(55));
        return employees.contains(new BrokenEmployee(55));
    }

    private static final class BrokenEmployee {
        private static int nextIdentityHash = 1;
        private final int id;
        private final int identityHash = nextIdentityHash++;
        private BrokenEmployee(int id) { this.id = id; }
        @Override public boolean equals(Object other) {
            return this == other || other instanceof BrokenEmployee employee && id == employee.id;
        }
        // Emulates distinct identity hash codes: equals says same ID, hashCode does not.
        @Override public int hashCode() { return identityHash; }
    }
}
