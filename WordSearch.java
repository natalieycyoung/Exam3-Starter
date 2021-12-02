/**
 * Exam 3
 * https://ucsd-cse11-f21.github.io/assignments/exam3.html
 *
 * @author Natalie Young
 * @since 2021-12-01
 */

import java.nio.file.*;
import java.io.IOException;	// error handling
import java.util.*;

/*
interface Query
{
	boolean matches(String str);
}

class Contains implements Query
{
	String keyword;

	Contains(String keyword)
	{
		this.keyword = keyword;
	}

	public boolean matches(String str)
	{
		return str.contains(this.keyword);
	}
}
*/
class FileHelper
{
	/**
	 * Takes a path to a file and returns all of the lines in the
	 * file as an array of strings, printing an error if it failed.
	 *
	 * @param path
	 */
    static String[] getLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
        } catch (IOException ioe) {
            System.err.println("Error reading file " + path + ": " + ioe);
            return new String[] { "Error reading file " + path + ": " + ioe };
        }
    }
}

class WordSearch
{

	public static void main(String[] args)
	{
		String path = args[0];

		int fileCount = args.length;
/*
		String[] files = new String[fileCount];

		for (int i = 0; i < fileCount; i++)
		{
			files[i] = args[i];
		}
*/
		String[] words = FileHelper.getLines(path);
		List<String> searchTerms = new ArrayList<>(Arrays.asList(words));
		List<Integer> occurrences = new ArrayList<>();

		for (int i = 0; i < searchTerms.size(); i++)
		{
			occurrences.add(i, 1);
			System.out.println(searchTerms.get(i));
			System.out.println(occurrences.get(i));
		}


		for (String argument : args)
		{
			words = FileHelper.getLines(argument);
			
			for (int i = 0; i < words.length; i++)
			{
				if (searchTerms.contains(words[i]))
				{
					searchTerms.update(words[i], searchTerms.find(words[i]) + 1);
				}
			}
		}
		/*
		int filesToSearch = args.length;

		System.out.println("queryPath: " + queryPath);
		System.out.println("filesToSearch: " + filesToSearch);
*/


//	ComparatorLookupTable clt = new ComparatorLookupTable();
	}
}
