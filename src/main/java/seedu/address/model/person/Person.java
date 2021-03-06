package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book. Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Nric nric;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Date date;
    private final Address address;
    private final Description description;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Person(Name name, Date date, Nric nric, Phone phone,
                  Email email, Address address, Description description, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, date, nric, phone, email, address, description, tags);
        this.name = name;
        this.date = date;
        this.nric = nric;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
        this.remark = remark;
        this.tags.addAll(tags);

    }

    public Name getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Nric getNric() {
        return nric;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Description getDescription() {
        return description;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same. This
     * defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        //Persons must have unique Nric, phone number and email.
        return otherPerson != null
            && (otherPerson.getNric().equals(getNric())
                    || otherPerson.getPhone().equals(getPhone())
                    || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields. This defines a stronger notion of equality
     * between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
            && otherPerson.getNric().equals(getNric())
            && otherPerson.getPhone().equals(getPhone())
            && otherPerson.getEmail().equals(getEmail())
            && otherPerson.getAddress().equals(getAddress())
            && otherPerson.getDescription().equals(getDescription())
            && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, nric, phone, email, address, description, remark, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Report Date: ")
            .append(getDate())
            .append(" Nric: ")
            .append(getNric())
            .append(" Phone: ")
            .append(getPhone())
            .append(" Email: ")
            .append(getEmail())
            .append(" Address: ")
            .append(getAddress())
            .append(" Description: ")
            .append(getDescription())
            .append(" Remark: ")
            .append(getRemark())
            .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
