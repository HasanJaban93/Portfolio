package be.myportfolio.tasks.changes;

import be.myportfolio.tasks.model.State;
import jakarta.validation.constraints.NotNull;

public record StateChange(@NotNull State state) {
}
