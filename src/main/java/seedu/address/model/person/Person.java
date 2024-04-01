package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Comparable<Person> {

    // Identity fields
    private EntryList entryList = new EntryList();
    private Set<Tag> tags = new HashSet<>();

    private String toCompare = "";

    /**
     * Every field must be present and not null.
     */
    public Person(Entry name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        entryList.add(name);
        this.tags.addAll(tags);
    }
    /**
     * Every field must be present and not null.
     * This constructor is used for testing in PersonBuilder
     */
    public Person(EntryList entryList, Set<Tag> tags) {
        this.entryList = entryList;
        this.tags.addAll(tags);
    }

    /**
     * Adds an entry into the list, and then sorts the list
     * @param entry entry to be added
     */
    public void addEntry(Entry entry) {
        entryList.add(entry);
        entryList.sort();

    }

    public Entry getEntry(String category) {
        return entryList.get(category);
    }

    public void deleteEntry(String category) {
        entryList.delete(category);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return false;
    }

    /**
     * Sets the new comparator for each person for group function
     * @param category
     */
    public void toCompare(String category) {
        Entry entry = this.getEntry(category);
        if (entry == null) {
            toCompare = "";
            return;
        }
        toCompare = entry.getDescription();
    }

    public int compareTo(Person person) {
        return toCompare.compareTo(person.getToCompare());
    }

    public String getToCompare() {
        return toCompare;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getEntry("Name"), getEntry("Phone"), getEntry("Email"), getEntry("Address"), tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
    public EntryList getList() {
        return entryList;
    }

    public void setList(EntryList e) {
        entryList = e;
    }
}
