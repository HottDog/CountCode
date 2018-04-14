package parseMachine;

/**
 * Created by 袁江超 on 2018/4/13.
 */
public interface IStatus {
    public abstract IStatus Transform(ParseMachine.Action action);
    public abstract ParseMachine.Type GetType();
}
