package com.company;

class Black_Jewfish extends Fish {
    public String make_noice(){
        return "Blub, I'm a Black Jewfish!";
    }
    @Override
    public String toString(){
        //returns the Name of the class
        return (this.getClass().getSimpleName()) + ": " + this.make_noice();
    }
}
class Sierra_Mackerel extends Fish {
    public String make_noice(){
        return "Blub, I'm a Sierra Mackerel!";
    }
    @Override
    public String toString(){
        //returns the Name of the class
        return (this.getClass().getSimpleName()) + ": " + this.make_noice();
    }
}
class Swordfish extends Fish {
    public String make_noice(){
        return "Blub, I'm a Swordfish!";
    }
    @Override
    public String toString(){
        //returns the Name of the class
        return (this.getClass().getSimpleName()) + ": " + this.make_noice();
    }
}
class Flounder extends Fish {
    public String make_noice(){
        return "Blub, I'm a Flounder!";
    }
    @Override
    public String toString(){
        //returns the Name of the class
        return (this.getClass().getSimpleName()) + ": " + this.make_noice();
    }
}
class Lionfish extends Fish {
    public String make_noice(){
        return "Blub, I'm a Lionfish!";
    }
    @Override
    public String toString(){
        //returns the Name of the class
        return (this.getClass().getSimpleName()) + ": " + this.make_noice();
    }
}