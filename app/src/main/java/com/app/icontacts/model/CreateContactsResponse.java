package com.app.icontacts.model;

/**
 * Created by saili on 4/3/2018.
 */

public class CreateContactsResponse {
    private String id;

    private String createdAt;

    private String name;

    private String job;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

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
        return "ClassPojo [id = "+id+", createdAt = "+createdAt+", name = "+name+", job = "+job+"]";
    }
}
