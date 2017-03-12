package com.nameless.helpers;

/**
 * Created by wwwtm on 09.03.2017.
 */
public class TagNavigatorBuilder
{
    private int recordsPerPage;
    private int overallRecords;
    private int currentPage;
    private String hrefBase;

    private int startPageToDisplay;
    private int endPageToDisplay;

    private short adjacents = 2;
    private short visibleNavLinks = 5;

    public TagNavigatorBuilder(int page, int recordsPerPage, int overallRecords, String hrefBase)
    {
        this.currentPage = page;
        this.recordsPerPage = recordsPerPage;
        this.overallRecords = overallRecords;
        this.hrefBase = hrefBase;

        init();
    }

    private void init()
    {
        startPageToDisplay = 1;

        if (overallRecords <= recordsPerPage)
        {
            endPageToDisplay = 1;
            return;
        }

        int lastPage = overallRecords / recordsPerPage;

        if (overallRecords % recordsPerPage != 0)
        {
            lastPage++;
        }

        endPageToDisplay = lastPage;

        if (lastPage <= visibleNavLinks)
            return;

        if ((currentPage + adjacents) <= lastPage)
            endPageToDisplay = currentPage + adjacents;

        if ((currentPage - adjacents) >= 1)
            startPageToDisplay = currentPage - adjacents;

        if ((currentPage - adjacents) < 1)
            endPageToDisplay = adjacents * 2 + 1;

        if ((currentPage + adjacents) > lastPage)
            startPageToDisplay = lastPage - adjacents * 2;

    }

    public String build()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"pagination\">");

        for (int i = startPageToDisplay; i <= endPageToDisplay; i++)
        {
            if (i == currentPage)
            {
                sb.append(String.format("<a class=\"current\">%d</a>", i));
                continue;
            }

            sb.append(String.format("<a href =\"%2$s/%1$d\">%1$d</a>", i, hrefBase));
        }

        sb.append("</div>");

        return sb.toString();
    }

    public void setVisibleNavLinks(short visibleNavLinks)
    {
        this.visibleNavLinks = visibleNavLinks;
        init();
    }

    public void setAdjacents(short adjacents)
    {
        this.adjacents = adjacents;
        init();
    }
}
