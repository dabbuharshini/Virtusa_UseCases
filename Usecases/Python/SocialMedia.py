posts=[
    {"id":1,"user":"User123","content":"I hate this platform http://badlink.com","likes":10},
    {"id":2,"user":"User456","content":"This is a good day","likes":20},
    {"id":3,"user":"User789","content":"Check out my new blog post http://myblog.com","likes":5},
    {"id":4,"user":"User012","content":"I love this community","likes":15},
    {"id":5,"user":"User345","content":"This is terrible http://terrible.com","likes":2},
    {"id":6,"user":"User678","content":"Had a great day at the park!","likes":25},
    {"id":7,"user":"User901","content":"I love this app but some people are so rude!","likes":8},
    {"id":8,"user":"User234","content":"Check out my new video http://myvideo.com","likes":12},
    {"id":9,"user":"User567","content":"This is the worst experience ever!","likes":1},
    {"id":10,"user":"User890","content":"such a toxic environment","likes":3},
]
banned_words=["bad","toxic","hate","terrible","worst","rude"]

total_posts=len(posts)
refreshed_posts=0
block_posts=0

user_flags={}
all_links=[]
cleaned_data=[]

for post in posts:
    user_flags[post["user"]]=0

# processing posts
for post in posts:
    org_content=post["content"]
    content=org_content
    lower_content=content.lower()
    is_blocked=False
    #word masking
    for word in banned_words:
        if word in lower_content:
            is_blocked=True
            content=content.replace(word,"****")
            content=content.replace(word.capitalize(),"****")

    if is_blocked:
        block_posts+=1
        user_flags[post["user"]]+=1
    else:
        refreshed_posts+=1

    #Link extraction
    words=org_content.split()
    for w in words:
        if w.startswith("http://") or w.startswith("https://"):
            all_links.append(w)

    # store clean data
    cleaned_data.append(
{
            "id":post["id"],
            "user":post["user"],
            "content":content,
            "likes":post["likes"]
        }
    )
#save cleaned data and links to files
with open("cleaned_posts.txt","w") as f:
    for post in cleaned_data:
        f.write(f"ID:{post['id']}|{post['content']}|Likes:{post['likes']}\n")

#save links to file
with open("links_found.txt","w") as f:
    for link in all_links:
        f.write(f"{link}\n")

#Final report
print("\n---Moderation Report---")
print(f"Total posts: {total_posts}")
print(f"Refreshed posts: {refreshed_posts}")
print(f"Blocked posts: {block_posts}")

print("\n---User flags---")
for user_id, flags in user_flags.items():
    print(f"User {user_id}: {flags} flags")

print("\n---Cleaned posts---")
for post in cleaned_data:
    print(f"ID: {post['id']}, Content: {post['content']}, Likes: {post['likes']}")

print("\n---Links found---")
for link in all_links:
    print(link)

