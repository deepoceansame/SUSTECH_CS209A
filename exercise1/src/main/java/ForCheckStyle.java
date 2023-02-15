public class ForCheckStyle {

    class InnerDyClass {

        int field1;

        public InnerDyClass () {
            field1 = 100;
        }
    }

    static class InnerStClass {

        int field1;

        public InnerStClass () {
            field1 = 9;
        }

    }

    public InnerDyClass makeADyClass (){
        return new InnerDyClass();
    }

    public static void main(String[] args) {
//        InnerDyClass innerDyClass = new InnerDyClass();
        ForCheckStyle forCheckStyle = new ForCheckStyle();
        InnerDyClass innerDyClass = forCheckStyle.makeADyClass();
        System.out.println(innerDyClass.field1);
        InnerStClass innerStClass = new InnerStClass();
        System.out.println(innerStClass.field1);
    }


}

class OuterDyClass {

}

// public static class OuterStClass {
//
//}