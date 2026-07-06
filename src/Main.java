//TIP Чтобы <b>запустить</b> код, нажмите <shortcut actionId="Run"/> или
// нажмите на значок <icon src="AllIcons.Actions.Execute"/> на полях.
void main() {

    IO.println(String.format("oop__object-oriented programming "));

    for (int i = 1; i <= 5; i++) {
        //TIP Нажмите <shortcut actionId="Debug"/>, чтобы начать отладку кода. Мы установили одну <icon src="AllIcons.Debugger.Db_set_breakpoint"/> точку останова
        // для вас, но вы всегда можете добавить больше, нажав <shortcut actionId="ToggleLineBreakpoint"/>.
        IO.println("i = " + i);
    }
}
