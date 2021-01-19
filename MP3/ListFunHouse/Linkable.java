// Name - Jordan Wang
// Prog - Linkable Interface
// Spec - Interface with various methods to be implemented

public interface Linkable
{
   Comparable getValue();
   Linkable getNext();
   void setNext(Linkable next);
   void setValue(Comparable value);
}