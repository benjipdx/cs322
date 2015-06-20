class NotTest
{
  public static void main(String[] args) {
    Stmt s
     = new Seq(new Assign("t", new Int(1)),
     new Seq(new Assign("i", new Int(1)),
       new Seq(new While(new Not(new LTE(new Int(10), new Var("i"))),
                         new Seq(new Assign("t", new Mult(new Var("t"), new Var("i"))),
                                 new Assign("i", new Plus(new Var("i"), new Int(1))))),
               new Print(new Var("t")))));

    System.out.println("Complete program is:");
    s.print(4);

    System.out.println("Running on an empty memory:");
    Memory mem = new Memory();
    s.exec(mem);

    System.out.println("Compiling:");
    Program p     = new Program();
    Block   entry = p.block(s.compile(p, new Stop()));
    System.out.println("Entry point is at " + entry);
    p.show();

    System.out.println("Running on an empty memory:");
    mem      = new Memory();
    Block pc = entry;
    while (pc!=null)  {
      pc = pc.code().run(mem);
    } 

    System.out.println("Done!");
  }
}
