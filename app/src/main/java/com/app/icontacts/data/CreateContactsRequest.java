package com.app.icontacts.data;

/**
 * Created by saili on 4/3/2018.
 */

public class CreateContactsRequest {
    private String name;

    private String job;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getJob ()
    {
        return job;
    }

    public void setJob (String job)
    {
        this.job = job;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", job = "+job+"]";
    }
}
